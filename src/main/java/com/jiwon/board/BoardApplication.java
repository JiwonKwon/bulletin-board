package com.jiwon.board;

import com.jiwon.board.domain.Board;
import com.jiwon.board.domain.User;
import com.jiwon.board.repository.BoardRepository;
import com.jiwon.board.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableAutoConfiguration()
public class BoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository,
									BoardRepository boardRepository) throws Exception {
		return (args) -> {
			User user = userRepository.save(User.builder()
			.name("jiwon")
			.password("test")
			.email("jiwon@test.com")
			.createDate(LocalDateTime.now())
			.build());

			//board객체 200개 생성하여 저장해보기
			IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
					.title("게시글"+index)
					.content("내용내용")
					.createDate(LocalDateTime.now())
					.updateDate(LocalDateTime.now())
					.user(user).build()));
		};
	}

	@Bean
	public ClassLoaderTemplateResolver boardTemplateResolver(){
		ClassLoaderTemplateResolver boardTemplateResolver = new ClassLoaderTemplateResolver();
		boardTemplateResolver.setPrefix("templates/");
		boardTemplateResolver.setSuffix(".html");
		boardTemplateResolver.setTemplateMode("HTML5");
		boardTemplateResolver.setCharacterEncoding("UTF-8");
		boardTemplateResolver.setOrder(1);
		return boardTemplateResolver;
	}
}