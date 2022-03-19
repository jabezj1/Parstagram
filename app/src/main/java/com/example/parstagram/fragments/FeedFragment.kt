package com.example.parstagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.parstagram.MainActivity
import com.example.parstagram.Post
import com.example.parstagram.PostAdapter
import com.example.parstagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import okhttp3.Headers
import org.json.JSONException

open class FeedFragment : Fragment() {

    lateinit var rvPost : RecyclerView

    lateinit var adapter: PostAdapter
    var allPosts: MutableList<Post> = mutableListOf()

    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPost = view.findViewById(R.id.rvPost)

        adapter = PostAdapter(requireContext(),allPosts)
        rvPost.adapter = adapter

        rvPost.layoutManager = LinearLayoutManager(requireContext())


        swipeContainer = view.findViewById(R.id.swipeContainer)

        swipeContainer.setOnRefreshListener {
            Log.i(TAG, "Refresh Timeline")
            //populateHomeFeed()

        }

        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        queryPost()
    }

    // Query for all post in our server
    open fun queryPost(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //Find all Post objects
        query.include(Post.KEY_USER)
        query.setLimit(20)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if( e != null){
                    Log.e(TAG, "Error fetching posts")
                } else{
                    if (posts != null){
                        for (post in posts){
                            Log.i (TAG, "Post: " + post.getDescription() + ", username; " +
                                    post.getUser()?.username)
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }

                }

            }

        })
    }

    companion object{
        const val TAG  = "FeedFragment"
    }


}