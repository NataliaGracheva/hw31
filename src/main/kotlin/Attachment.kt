sealed class Attachment(open val type: Type)

enum class Type {
    AUDIO, VIDEO, PHOTO, FILE, NOTE
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int?,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean
)

class AudioAttachment(val audio: Audio) : Attachment(Type.AUDIO)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: Array<Image>,
    val firstFrame: Array<FirstFrame>,
    val date: Int,
    val addingDate: Int,
    val views: Int,
    val localViews: Int,
    val comments: Int?,
    val player: String,
    val platform: String?,
    val canAdd: Boolean,
    val isPrivate: Boolean,
    val accessKey: String,
    val processing: Boolean,
    val isFavourite: Boolean,
    val canComment: Boolean,
    val canEdit: Boolean,
    val canLike: Boolean,
    val canRepost: Boolean,
    val canSubscribe: Boolean,
    val canAddToFaves: Boolean,
    val canAttachLink: Boolean,
    val width: Int,
    val height: Int,
    val userId: Int,
    val converting: Boolean,
    val added: Boolean,
    val isSubscribed: Boolean,
    val repeat: Boolean,
    val type: VideoType,
    val balance: Int,
    val liveStatus: LiveStatus?,
    val live: Boolean,
    val upcoming: Boolean,
    val spectators: Int?,
    val likes: VideoLikes,
    val reposts: VideoReposts
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int,
    val withPadding: Int?
)

data class FirstFrame(
    val height: Int,
    val url: String,
    val width: Int
)

enum class VideoType {
    VIDEO, MUSIC_VIDEO, MOVIE
}

enum class LiveStatus {
    WAITING, STARTED, FINISHED, FAILED, UPCOMING
}

data class VideoLikes(
    val count: Int,
    val userLikes: Boolean
)

data class VideoReposts(
    val count: Int,
    val wallCount: Int,
    val mailCount: Int,
    val userReposted: Boolean
)

class VideoAttachment(val video: Video) : Attachment(Type.VIDEO)

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val sizes: Array<PhotoSize>,
    val width: Int,
    val height: Int
)

data class PhotoSize(
    val type: PhotoType,
    val url: String,
    val width: Int,
    val height: Int
)

enum class PhotoType {
    S, M, X, O, P, Q, R, Y, Z, W
}

class PhotoAttachment(val photo: Photo) : Attachment(Type.PHOTO)

data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String,
    val date: Int,
    val type: FileType,
    val preview: Preview
)

enum class FileType(i: Int) {
    TEXT(1), ARCHIVE(2), GIF(3), PICTURE(4), AUDIO(5), VIDEO(6), EBOOK(7), UNKNOWN(8)
}

data class Preview(
    val photo: PhotoPreview?,
    val graffiti: GraffitiPreview?,
    val audioMessage: AudioMessagePreview?
)

data class PhotoPreview(
    val sizes: Array<PhotoSize>
)

data class GraffitiPreview(
    val src: String,
    val width: Int,
    val height: Int
)

data class AudioMessagePreview(
    val duration: Int,
    val waveForm: Array<Int>,
    val linkOgg: String,
    val linkMp3: String
)

class FileAttachment(val file: File) : Attachment(Type.FILE)

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComments: Int?,
    val viewUrl: String,
    val privacyView: String,
    val canComment: Boolean,
    val textWiki: String
)

class NoteAttachment(val note: Note) : Attachment(Type.NOTE)