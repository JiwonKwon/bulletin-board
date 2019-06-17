package com.jiwon.board;

import com.jiwon.board.domain.Board;
import com.jiwon.board.domain.User;
import com.jiwon.board.repository.BoardRepository;
import com.jiwon.board.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@email.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
                .name("jiwon")
                .password("test")
                .email(email)
                .createDate(LocalDateTime.now())
                .build());

                    boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .content("내용이다!!!!")
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .user(user).build());
    }

    @Test
    public void generate_test() {
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(),is("jiwon"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getContent(), is("내용이다!!!!"));
    }
}
