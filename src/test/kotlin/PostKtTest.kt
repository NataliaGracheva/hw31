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
        postponedId = 0
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
}

