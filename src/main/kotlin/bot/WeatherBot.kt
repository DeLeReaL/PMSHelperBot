package bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import data.remote.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

private const val BOT_ANSWER_TIMEOUT = 30
private const val BOT_TOKEN = "5606700757:AAFHl1cuoaQSK8-YX76ipp3klQXcXV0WV8I"

class WeatherBot(private val weatherRepository: WeatherRepository ) {

    private  var _chatId: ChatId? = null
    private  val chatId: ChatId get() = requireNotNull(_chatId)

    fun createBot():Bot{
        return bot{
            timeout = BOT_ANSWER_TIMEOUT
            token = BOT_TOKEN

            dispatch {
                setUpCommands()
            }
        }
    }

    private fun Dispatcher.setUpCommands() {
        command("start"){
            _chatId = ChatId.fromId(message.chat.id)
            bot.sendMessage(
                chatId = chatId,
                text = "Hello, i'm bot"
            )

        }
    }
}
