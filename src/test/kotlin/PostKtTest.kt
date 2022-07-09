import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostKtTest {
    private val post = Post(
        id = 0,
        ownerId = 1,
        fromId = 1,
        createdBy = 0,
        date = 1656614136827,
        text = "test post 1",
        replyOwnerId = null,
        replyPostId = null,
        friendsOnly = false,
        comments = Comments(
            count = 0,
            canPost = true,
            groupsCanPost = true,
            canClose = true,
            canOpen = true
        ),
        copyright = null,
        likes = Likes(
            count = 0,
            userLikes = false,
            canLike = true,
            canPublish = true
        ),
        reposts = Reposts(
            count = 0,
            userReposted = false
        ),
        views = Views(count = 0),
        postType = PostType.POST,
        signerId = null,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = false,
        isFavorite = false,
        donut = null,
        postponedId = 0,
        attachments = null
    )

    @Before
    fun setUp() {
        WallService.clearPosts()
    }

    @Test
    fun shouldAddPost() {
        val added = WallService.add(post)
        Assert.assertEquals(1, added.id)
    }

    @Test
    fun shouldUpdatePostAndReturnTrue() {
        val updatedPost = post.copy(
            text = "test post updated",
            comments = post.comments.copy(count = 999),
            likes = post.likes.copy(count = 999),
            reposts = post.reposts.copy(count = 999),
            views = post.views.copy(count = 999),
            donut = post.donut?.copy(isDonut = true)
        )
        WallService.add(post)
        Assert.assertTrue(WallService.update(updatedPost.copy(id = 1)))
    }

    @Test
    fun shouldReturnFalseIfPostIsMissing() {
        Assert.assertFalse(WallService.update(post.copy(id = 1)))
    }

    @Test
    fun shouldAddPostWithAttachments() {
        val attachments: Array<Attachment> = arrayOf(
            AudioAttachment(
                audio = Audio(
                    id = 123,
                    ownerId = 123,
                    artist = "some artist",
                    title = "some title",
                    duration = 123,
                    url = "some url",
                    lyricsId = null,
                    albumId = 123,
                    genreId = 123,
                    date = 123,
                    noSearch = false,
                    isHq = false
                )
            ),
            VideoAttachment(
                video = Video(
                    id = 456,
                    ownerId = 456,
                    title = "some title",
                    description = "some description",
                    duration = 456,
                    image = arrayOf(Image(height = 456, url = "some url", width = 456, withPadding = null)),
                    firstFrame = arrayOf(FirstFrame(height = 456, url = "some url", width = 456)),
                    date = 456,
                    addingDate = 456,
                    views = 456,
                    localViews = 456,
                    comments = null,
                    player = "some value",
                    platform = null,
                    canAdd = false,
                    isPrivate = false,
                    accessKey = "some value",
                    processing = false,
                    isFavourite = false,
                    canComment = true,
                    canEdit = false,
                    canLike = true,
                    canRepost = true,
                    canSubscribe = true,
                    canAddToFaves = true,
                    canAttachLink = false,
                    width = 456,
                    height = 456,
                    userId = 456,
                    converting = false,
                    added = false,
                    isSubscribed = false,
                    repeat = false,
                    type = VideoType.VIDEO,
                    balance = 456,
                    liveStatus = LiveStatus.STARTED,
                    live = true,
                    upcoming = false,
                    spectators = 456,
                    likes = VideoLikes(count = 456, userLikes = false),
                    reposts = VideoReposts(count = 456, wallCount = 456, mailCount = 0, userReposted = false)
                )
            ),
            PhotoAttachment(
                photo = Photo(
                    id = 789,
                    albumId = 789,
                    ownerId = 789,
                    userId = 789,
                    text = "some text",
                    date = 789,
                    sizes = arrayOf(PhotoSize(type = PhotoType.S, url = "some url", width = 789, height = 789)),
                    width = 789,
                    height = 789
                )
            ),
            FileAttachment(
                file = File(
                    id = 999,
                    ownerId = 999,
                    title = "some title",
                    size = 999,
                    ext = "txt",
                    url = "some url",
                    date = 999,
                    type = FileType.TEXT,
                    preview = Preview(photo = null, graffiti = null, audioMessage = null)
                )
            ),
            NoteAttachment(
                note = Note(
                    id = 111,
                    ownerId = 111,
                    title = "some title",
                    text = "some text",
                    date = 111,
                    comments = 111,
                    readComments = null,
                    viewUrl = "some url",
                    privacyView = "some value",
                    canComment = true,
                    textWiki = ""
                )
            )
        )
        val postWithAttachment = post.copy(
            attachments = attachments
        )
        val added = WallService.add(postWithAttachment)
        Assert.assertArrayEquals(attachments, added.attachments)

    }
}

