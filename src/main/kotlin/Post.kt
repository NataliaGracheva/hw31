data class Post(
    val id: Int,
    val owner_id: Int,
    val from_id: Int,
    val created_by: Int,
    val date: Long,
    val text: String,
    val reply_owner_id: Int,
    val reply_post_id: Int,
    val friends_only: Boolean,
    val comments: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val post_type: PostType,
    val signer_id: Int,
    val can_pin: Boolean,
    val can_delete: Boolean,
    val can_edit: Boolean,
    val is_pinned: Boolean,
    val marked_as_ads: Boolean,
    val is_favorite: Boolean,
    val donut: Donut,
    val postponed_id: Int
)

data class Comments(
    val count: Int,
    val can_post: Boolean,
    val groups_can_post: Boolean,
    val can_close: Boolean,
    val can_open: Boolean
)

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

data class Likes(
    val count: Int,
    val user_likes: Boolean,
    val can_like: Boolean,
    val can_publish: Boolean
)

data class Reposts(
    val count: Int,
    val user_reposted: Boolean
)

data class Views(
    val count: Int
)

enum class PostType {
    POST, COPY, REPLY, POSTPONE, SUGGEST
}

data class Donut(
    val is_donut: Boolean,
    val paid_duration: Int,
    val placeholder: Placeholder,
    val can_publish_free_copy: Boolean,
    val edit_mode: EditMode
)

class Placeholder

enum class EditMode {
    all, duration
}

object WallService {

    private var counter: Int = 0
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += post.copy(id = ++counter)
        return posts.last()
    }

    fun update(post: Post): Boolean {
//        val p = posts.find { it.id == post.id }
//        return if (p != null) {
//            posts[posts.indexOf(p)] = post.copy(
//                from_id = post.from_id,
//                text = post.text,
//                postponed_id = post.postponed_id)
//            true
//        } else {
//            false
//        }

       for ((index, el) in posts.withIndex()) {
           if (el.id == post.id) {
               posts[index] = post.copy()
            return true
           }
       }
        return false
    }

    fun getPosts(): Array<Post> {
        return posts
    }

    fun clearPosts() {
        counter = 0
        posts = emptyArray()
    }
}
