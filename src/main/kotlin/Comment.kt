data class Comment(
    val id: Int = 0,
    val postId: Int = 0,
    val fromId: Int,
    val date: Long,
    val text: String,
    val donut: CommentDonut,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachments: Array<Attachment>? = null,
    val parentsStack: Array<Long>,
    val thread: Thread
)

data class CommentDonut(
    val isDonut: Boolean,
    val placeholder: String = "some text"
)

data class Thread(
    val count: Int,
    val items: Array<Long>,
    val canPost: Boolean,
    val showReplyButton: Boolean,
    val groupsCanPost: Boolean
)