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
        val p = posts.find { it.id == post.id }
        return if (p != null) {
            posts[posts.indexOf(p)] = post.copy(
                from_id = post.from_id,
                text = post.text,
                postponed_id = post.postponed_id)
            true
        } else {
            false
        }

    }

    fun getPosts(): Array<Post> {
        return posts
    }

    fun clearPosts() {
        counter = 0
        posts = emptyArray()
    }

    fun print() {
        println(posts.contentToString())
    }
}

fun main() {
    WallService.add(Post(
        id = 0,
        owner_id = 1,
        from_id = 1,
        created_by = 0,
        date = 1656614136827,
        text = "test post 1",
        reply_owner_id = 0,
        reply_post_id = 0,
        friends_only = false,
        comments = Comments(count = 0,
            can_post = true,
            groups_can_post = true,
            can_close = true,
            can_open = true),
        copyright = Copyright(id = 0,
            link = "",
            name = "",
            type = ""),
        likes = Likes(count = 0,
            user_likes = false,
            can_like = true,
            can_publish = true),
        reposts = Reposts(count = 0,
            user_reposted = false),
        views = Views(count = 0),
        post_type = PostType.POST,
        signer_id = 0,
        can_pin = true,
        can_delete = true,
        can_edit = true,
        is_pinned = false,
        marked_as_ads = false,
        is_favorite = false,
        donut = Donut(is_donut = false,
            paid_duration = 0,
            placeholder = Placeholder(),
            can_publish_free_copy = false,
            edit_mode = EditMode.all),
         postponed_id = 0))
    WallService.add(Post(
        id = 0,
        owner_id = 123,
        from_id = 1,
        created_by = 0,
        date = 1656614136999,
        text = "test post 2",
        reply_owner_id = 0,
        reply_post_id = 0,
        friends_only = true,
        comments = Comments(count = 1,
            can_post = true,
            groups_can_post = false,
            can_close = true,
            can_open = true),
        copyright = Copyright(id = 0,
            link = "",
            name = "",
            type = ""),
        likes = Likes(count = 1,
            user_likes = true,
            can_like = true,
            can_publish = true),
        reposts = Reposts(count = 1,
            user_reposted = true),
        views = Views(count = 1),
        post_type = PostType.COPY,
        signer_id = 0,
        can_pin = true,
        can_delete = true,
        can_edit = true,
        is_pinned = true,
        marked_as_ads = false,
        is_favorite = false,
        donut = Donut(is_donut = false,
            paid_duration = 0,
            placeholder = Placeholder(),
            can_publish_free_copy = false,
            edit_mode = EditMode.all),
        postponed_id = 12345))
    WallService.print()
    WallService.update(Post(
        id = 2,
        owner_id = 123,
        from_id = 1,
        created_by = 0,
        date = 1656620000000,
        text = "test post 2 updated",
        reply_owner_id = 0,
        reply_post_id = 0,
        friends_only = true,
        comments = Comments(count = 999,
            can_post = true,
            groups_can_post = false,
            can_close = true,
            can_open = true),
        copyright = Copyright(id = 0,
            link = "",
            name = "",
            type = ""),
        likes = Likes(count = 999,
            user_likes = true,
            can_like = true,
            can_publish = true),
        reposts = Reposts(count = 999,
            user_reposted = true),
        views = Views(count = 999),
        post_type = PostType.COPY,
        signer_id = 0,
        can_pin = true,
        can_delete = true,
        can_edit = true,
        is_pinned = true,
        marked_as_ads = false,
        is_favorite = false,
        donut = Donut(is_donut = false,
            paid_duration = 0,
            placeholder = Placeholder(),
            can_publish_free_copy = false,
            edit_mode = EditMode.all),
        postponed_id = 12345))
    WallService.print()
    WallService.clearPosts()
    WallService.print()
}
