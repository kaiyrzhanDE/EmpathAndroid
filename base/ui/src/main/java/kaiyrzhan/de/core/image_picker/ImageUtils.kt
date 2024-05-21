package kaiyrzhan.de.core.image_picker

import android.content.Context
import android.content.ContentUris
import android.os.Environment
import android.provider.MediaStore
import kaiyrzhan.de.core.image_picker.model.Image
import java.io.File

object ImageUtils {

    fun getGalleryImages(context: Context): List<Image> {
        val protection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
        )
        val path = "${Environment.DIRECTORY_PICTURES}${File.separator}%"
        val selection =  "${MediaStore.Files.FileColumns.RELATIVE_PATH} like ? " ;
        val selectionArgs = arrayOf(path)
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        val images = mutableListOf<Image>()
        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            protection,
            selection,
            selectionArgs,
            sortOrder,
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id,
                )
                images.add(
                    Image(id, name, uri)
                )
            }
        }
        return images
    }

}