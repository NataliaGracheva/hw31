data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int?,
    val date: Long,
    val text: String,
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean,
    val comments: Comments,
    val copyright: Copyright?,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: PostType,
    val signerId: Int?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Donut?,
    val postponedId: Int,
    val attachments: Array<Attachment>?
)

data class Comments(
    val count: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean,
    val canOpen: Boolean
)

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)

data class Reposts(
    val count: Int,
    val userReposted: Boolean
)

data class Views(
    val count: Int
)

enum class PostType {
    POST, COPY, REPLY, POSTPONE, SUGGEST
}

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Placeholder,
    val canPublishFreeCopy: Boolean,
    val editMode: EditMode
)

class Placeholder

enum class EditMode {
    ALL, DURATION
}

object WallService {

    private var counter: Int = 0
    private var posts = emptyArray<Post>()
    private var commentCounter: Int = 0
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<Report>()

    fun add(post: Post): Post {
        posts += post.copy(id = ++counter)
        return posts.last()
    }

    fun update(post: Post): Boolean {

        for ((index, el) in posts.withIndex()) {
            if (el.id == post.id) {
                posts[index] = post.copy(ownerId = el.ownerId, date = el.date)
                return true
            }
        }
        return false
    }

    fun clear() {
        counter = 0
        posts = emptyArray()
        commentCounter = 0
        comments = emptyArray()
        reports = emptyArray()
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for (el in posts) {
            if (el.id == postId) {
                comments += comment.copy(id = ++commentCounter, postId = postId)
                return comments.last()
            }
        }
        throw PostNotFoundException("No post with id $postId")
    }

    fun reportComment(ownerId: Int, commentId: Int, reason: Int) : Boolean {
        if (reason !in 0..8) {
            throw WrongReasonException("Unexpected reason $reason")
        }
        for (el in comments) {
            if (el.id == commentId) {
                reports += Report(ownerId, commentId, reason)
                return true
            }
        }
        throw CommentNotFoundException("No comment with id $commentId")
    }
}

class PostNotFoundException(message: String) : RuntimeException(message)

class CommentNotFoundException(message: String) : RuntimeException(message)

class WrongReasonException(message: String) : RuntimeException(message)
