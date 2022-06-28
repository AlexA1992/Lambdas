import org.junit.Test

import org.junit.Assert.*

class chatsTest {

    @Test
    fun createChat() {
        //arrange
        val message1 = Message(1, 1, false, "first message")
        //act
        val startChats = chats.allChats.size
        chats.addMessageToChat(message1)
        val finishChats = chats.allChats.size
        //assert
        assertEquals(finishChats > startChats, true)
    }

    @Test
    fun deleteChat() {
        //arrange
        val message1 = Message(1, 1, false, "first message")
        chats.addMessageToChat(message1)
        val startChats = chats.allChats.size
        //act
        chats.deleteChat(chats.allChats.get(0))
        val finishChats = chats.allChats.size
        //assert
        assertEquals(finishChats < startChats, true)
    }

    @Test
    fun deleteMessage() {
        //arrange
        val message1 = Message(1, 1, false, "first message")
        val message2 = Message(2, 1, true, "second message")
        val message3 = Message(3, 2, false, "third message")
        chats.addMessageToChat(message1)
        chats.addMessageToChat(message2)
        chats.addMessageToChat(message3)
        val startMes = chats.allMessages.size
        println(chats.allMessages)
        //act
        chats.deleteMessage(3)
        val finishMes = chats.allMessages.size
        println(chats.allMessages)
        //assert
        assertEquals(finishMes < startMes, true)
    }


}