import chats.allChats
import messages.allMessages

data class Message(
    val id: Int, val userId: Int, var seen: Boolean, var text: String
)

class Chat(
    val id: Int, val userId: Int, val itsMessages: MutableList<Message>
)

object chats {
    val allChats = mutableListOf<Chat>()

    // создать чат
    fun createChat(message: Message, chats: MutableList<Chat>): Chat {
        val lastChatId = chats.last().id
        val newChatId = lastChatId + 1
        val messages = mutableListOf<Message>()
        messages.add(message)
        val newChat = Chat(newChatId, message.userId, messages)
        return newChat
    }

    // добавить чат в коллекцию чатов
    fun addToChatList(chat: Chat) {
        allChats.add(chat)
    }

    // удалить чат
    fun deleteChat(chat: Chat) {
        allChats.remove(chat)
    }

    // печатаем все чаты
    fun printAllChats(allChats: MutableList<Chat>) {
        println("All chats")
        for (chat in allChats) {
            println(chat.itsMessages)
        }
    }

    // печатаем все чаты где есть хоть 1 непрочитанное сообщение
    fun printAllChatsWithUnreadMessages(allChats: MutableList<Chat>) {
        println("Все чаты с непрочитанными сообщениями")
        val mesIterator = chats.allChats.iterator()
        var unreadChatsQuant = 0
        while (mesIterator.hasNext()) {
            val checkigChat = mesIterator.next()
            val chekingMessage: MutableList<Message> = checkigChat.itsMessages
            val messagesToFind: MutableList<Message> =
                chekingMessage.filter { it.seen == false } as MutableList<Message>
            if (!messagesToFind.isEmpty()) {
                unreadChatsQuant = unreadChatsQuant + 1
                println(checkigChat.itsMessages)
                //break
            }
            continue
        }
        println(unreadChatsQuant)
    }
}

object messages {
    val allMessages = mutableListOf<Message>()

    //удаляем сообщение по id и, если оно последнее, то весь чат тоже
    fun deleteMessage(messageId: Int) {
        println("Удаляем сообщение по Id")
        val chatsIterator = chats.allChats.iterator()
        while (chatsIterator.hasNext()) {
            val checkigChat = chatsIterator.next()
            val chekingMessage: MutableList<Message> = checkigChat.itsMessages
            val messagesToDelete: MutableList<Message> =
                chekingMessage.filter { it.id == messageId } as MutableList<Message>
            if (!messagesToDelete.isEmpty()) {
                val messageToDelete: Message? = messagesToDelete.get(0)
                println(chekingMessage)
                chekingMessage.remove(messageToDelete)
                println("************")
                //проверяем удалено ли сообщение
                println(chekingMessage)
                if (checkigChat.itsMessages.isEmpty()) chats.deleteChat(checkigChat)
                break
            }
            continue
        }
    }

    // посмотреть все непрочитанные сообщения
    fun printUnseenMessages(allMessages: MutableList<Message>) {
        println("Все непросмотренные сообщения")
        val unseen: MutableList<Message> = allMessages.filter { it.seen == false } as MutableList<Message>
        for (mes in unseen) {
            println(mes)
        }
    }

    // печатаем все сообщения подряд
    fun printAllMessages(allMessages: MutableList<Message>) {
        println("All messages")
        for (message in allMessages) {
            println(message)
        }
    }

    // печатаем все сообщения по Id
    fun printAllMessagesOfChatByMessageId(allChats: MutableList<Chat>, messageId: Int) {
        println("Все сообщения чата по id сообщения")
        val mesIterator = chats.allChats.iterator()
        while (mesIterator.hasNext()) {
            val checkigChat = mesIterator.next()
            val chekingMessage: MutableList<Message> = checkigChat.itsMessages
            val messagesToFind: MutableList<Message> =
                chekingMessage.filter { it.id == messageId } as MutableList<Message>
            if (!messagesToFind.isEmpty()) {
                val messageCount = messagesToFind.count()
                for (mes in messagesToFind) {
                    println(mes.text)
                }
                println(messageCount)
                break
            }
            continue
        }
    }

    // добавляем сообщение в чат
    fun addMessageToChat(message: Message) {
        allMessages.add(message)
        if (chats.allChats.isEmpty()) {
            val userId = message.userId
            val chatMessages = mutableListOf<Message>()
            chatMessages.add(message)
            val theChat = Chat(1, userId, chatMessages)
            chats.addToChatList(theChat)
        } else {
            val thisChat: Chat? = chats.allChats.firstOrNull { it.userId == message.userId }
            if (thisChat == null) {
                val newChat = chats.createChat(message, chats.allChats)
                chats.addToChatList(newChat)
            } else {
                thisChat.itsMessages.add(message)
            }
        }
    }
}

fun main() {
    val message1 = Message(1, 1, false, "first message")
    val message2 = Message(2, 1, true, "second message")
    val message3 = Message(3, 2, false, "third message")
    val message4 = Message(4, 2, false, "forth message")
    val message5 = Message(5, 3, true, "fifth message")
    val message6 = Message(6, 4, true, "sixth message")
    val message7 = Message(7, 4, false, "seventh message")
    messages.addMessageToChat(message1)
    messages.addMessageToChat(message2)
    messages.addMessageToChat(message3)
    messages.addMessageToChat(message4)
    messages.addMessageToChat(message5)
    messages.addMessageToChat(message6)
    messages.addMessageToChat(message7)

    println("----------------------")
    chats.printAllChats(allChats)
//    println("----------------------")
//    messages.printAllMessages(allMessages)
//    println("----------------------")
//    messages.printUnseenMessages(allMessages)

    // удаляем первый чат, где хранятся message1 и message2
//    chats.deleteChat(chats.allChats.get(0))
//    println("----------------------")
//    chats.printAllChats(allChats)
//    println("----------------------")
//    messages.deleteMessage(1)
//    println("----------------------")
//    messages.printAllMessages(allMessages)
//    println("----------------------")
//    chats.printAllChats(allChats)
//
//    //удаляем сообщение с id = 5
//    println("----------------------")
//    messages.deleteMessage(5)
//
//    println("----------------------")
//    chats.printAllChats(allChats)
//
//    // распечатать чат по id сообщения и количество сообщений
//    println("----------------------")
//    messages.printAllMessagesOfChatByMessageId(allChats, 7)

    // распечатать все чаты где есть непрочитанные сообщения
    chats.printAllChatsWithUnreadMessages(allChats)


}