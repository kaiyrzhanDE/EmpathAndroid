package kaiyrzhan.de.core.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(formatter.format(value))
    }

    override fun deserialize(decoder: Decoder): Date {
        val dateString = decoder.decodeString()
        return requireNotNull(formatter.parse(dateString)) { "Invalid date format: $dateString" }
    }
}
