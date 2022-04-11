import org.junit.Test

import org.junit.Assert.*

class chatsTest {

    @Test
    fun printAllChats() {
//arrange
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
        val allChats = mutableListOf<Chat>()
        //act
        val result = chats.printAllChats(allChats)

        //assert
        assertEquals(chats.printAllChats(allChats), result)
    }
}