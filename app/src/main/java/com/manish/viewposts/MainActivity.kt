package com.manish.viewposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.manish.viewposts.core.SecureBaseActivity
import com.manish.viewposts.core.ViewState
import com.manish.viewposts.databinding.ActivityMainBinding
import com.manish.viewposts.databinding.PostItemBinding
import com.manish.viewposts.network.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : SecureBaseActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.data.observe(this) { posts ->
            binding.recyclerView.adapter = PostAdapter(posts)
        }

        binding.tryAgainBtn.setOnClickListener {
            mainViewModel.fetchData()
        }

        mainViewModel.viewStateLiveData.observe(this) { viewState ->
            when (viewState.state) {
                ViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorLayout.visibility = View.GONE
                }
                ViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLayout.visibility = View.VISIBLE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLayout.visibility = View.GONE
                }
            }
        }
    }
}

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userIdTextView.text = "User ID: ${posts[position].userId}"
        holder.binding.idTextView.text = "ID: ${posts[position].id}"
        holder.binding.titleTextView.text = "Title: ${posts[position].title}"
        holder.binding.bodyTextView.text = "Body: ${posts[position].body}"
    }

    override fun getItemCount() = posts.size
}
