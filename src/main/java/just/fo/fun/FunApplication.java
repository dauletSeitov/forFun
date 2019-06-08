package just.fo.fun;

import just.fo.fun.category.repository.CategoryRepository;
import just.fo.fun.commentary.repository.CommentaryRepository;
import just.fo.fun.dss.*;
import just.fo.fun.entities.Category;
import just.fo.fun.entities.Commentary;
import just.fo.fun.entities.Post;
import just.fo.fun.entities.User;
import just.fo.fun.post.repository.PostRepository;
import just.fo.fun.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

//@ComponentScan(basePackages = {"just.fo.fun", "just.fo.fun.auth",  "just.fo.fun.auth.controller"})
@SpringBootApplication

public class FunApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FunApplication.class, args);
        new InitDB(context).fillDb();
    }
}
@Slf4j
class InitDB{


    private ConfigurableApplicationContext context;

    public InitDB(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public void fillDb() {
        fillUser(100);
        fillCategory(20);
        fillPost(1000);
        //fillCommentary(1000);

        fillDssWords();
        log.info("initialization is finished !");
    }

    private void fillDssWords() {

        initFromFilePositive();
        initFromFileNegative( );

    }
    private void initFromFilePositive() {

        DssPositiveWordRepository dssPositiveWordRepository = context.getBean(DssPositiveWordRepository.class);

        if(0 < dssPositiveWordRepository.findAll().size()) return;

        URL resource = DssService.class.getResource("/source/positive.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getPath()))) {

            String line;
            while ((line = br.readLine()) != null) {
                dssPositiveWordRepository.save(new DssPositiveWord(line));
            }

        } catch (IOException e) {
            log.error("IOException: %s%n", e);
        }
    }

    private void initFromFileNegative() {

        DssNegativeWordRepository dssNegativeWordRepository = context.getBean(DssNegativeWordRepository.class);

        if(0 < dssNegativeWordRepository.findAll().size()) return;

        URL resource = DssService.class.getResource("/source/negative.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getPath()))) {

            String line;
            while ((line = br.readLine()) != null) {
                dssNegativeWordRepository.save(new DssNegativeWord(line));
            }

        } catch (IOException e) {
            log.error("IOException: %s%n", e);
        }
    }
    private void fillUser(int len){
        UserRepository repository = context.getBean(UserRepository.class);

        if(len <= repository.findAll().size()) return;

        for (int i = 0; i < len ; i++) {

            User user = new User();
            user.setLogin(randomRow("login"));
            user.setName(randomRow("name"));
            user.setPassword(randomRow("pas"));

            try {
                repository.save(user);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void fillCategory(int len){

        CategoryRepository repository = context.getBean(CategoryRepository.class);

        if(len <= repository.findAll().size()) return;

        for (int i = 0; i < len ; i++) {

            Category category = new Category();
            category.setName(randomRow("CN"));

            try {
                repository.save(category);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void fillPost(int len){

        PostRepository repository = context.getBean(PostRepository.class);

        if(len <= repository.findAll().size()) return;

        UserRepository userRepository = context.getBean(UserRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

        List<User> userList = userRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();
        int userCount = userList.size();
        int categoryCount = categoryList.size();

        for (int i = 0; i < len ; i++) {

            Post post = new Post();
            post.setCategory(categoryList.get(ThreadLocalRandom.current().nextInt(1, categoryCount-1)));
            post.setImageUrl(randomRow("image"));
            post.setRating(ThreadLocalRandom.current().nextLong(-10000, 10000));
            post.setTitle(randomRow("title"));
            post.setUser(userList.get(ThreadLocalRandom.current().nextInt(1, userCount-1)));

            try {
                repository.save(post);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void fillCommentary(int len){

        CommentaryRepository repository = context.getBean(CommentaryRepository.class);

        if(len <= repository.findAll().size()) return;

        UserRepository userRepository = context.getBean(UserRepository.class);
        List<User> userList = userRepository.findAll();
        int userCount = userList.size();

        Post post = context.getBean(PostRepository.class).findOne(1L);

        for (int i = 0; i < len ; i++) {

            List<Commentary> parentList = repository.findAll();
            int parentCount = parentList.size();

            Commentary commentary = new Commentary();
            Commentary parent;

            try {
                parent = parentList.get(ThreadLocalRandom.current().nextInt(1, parentCount-1));
                if(ThreadLocalRandom.current().nextInt(1, 10) == 5){
                    parent = null;
                }
            }
            catch (Exception e){
                parent = null;
            }


            commentary.setImageUrl(randomRow("image"));
            commentary.setParent(parent);
            commentary.setPost(post);
            commentary.setRating(ThreadLocalRandom.current().nextLong(-10000, 10000));
            commentary.setText(randomRow("text"));
            commentary.setUser(userList.get(ThreadLocalRandom.current().nextInt(1, userCount-1)));

            try {
                repository.save(commentary);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String randomRow (String str){
        return str + UUID.randomUUID().toString().substring(0, 7);
    }

}


