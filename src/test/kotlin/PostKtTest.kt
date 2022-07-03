import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostKtTest {

    @Before
    fun setUp() {
        WallService.clearPosts()
    }

    @Test
    fun shouldAddPost() {
        val post = Post(
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
            postponed_id = 0)
        WallService.add(post)
        WallService.print()
        Assert.assertEquals(1, WallService.getPosts().size)
        Assert.assertTrue(WallService.getPosts().contains(post.copy(id = 1)))
    }

    @Test
    fun shouldUpdatePostAndReturnTrue() {
        val post = Post(
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
            postponed_id = 12345)
        val updatedPost = post.copy(date = 1656620000000,
            text = "test post 2 updated",
            comments = post.comments.copy(count = 999),
            likes = post.likes.copy(count = 999),
            reposts = post.reposts.copy(count = 999),
            views = post.views.copy(count = 999))
        WallService.add(post)
        val bool = WallService.update(updatedPost.copy(id = 1))
        Assert.assertTrue(bool)
        WallService.print()
        Assert.assertEquals(1, WallService.getPosts().size)
        Assert.assertTrue(WallService.getPosts().contains(updatedPost.copy(id = 1)))
    }

    @Test
    fun shouldReturnFalseIfPostIsMissing() {
        val post = Post(
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
            postponed_id = 0)
        Assert.assertEquals(0, WallService.getPosts().size)
        Assert.assertFalse(WallService.update(post.copy(id = 1)))
    }
}

