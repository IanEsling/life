package life.board;

/**
 */
public interface BoardListener
{
    static final BoardListener NULL_BOARD_LISTENER = new BoardListener(){
        public void boardUpdated()
        {
        }
    };

    void boardUpdated() throws InterruptedException;
}
