package com.dging.dgingmarket.web.api.base;

import com.dging.dgingmarket.config.MockS3Configuration;
import com.dging.dgingmarket.domain.common.ImageRepository;
import com.dging.dgingmarket.domain.common.TagRepository;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.service.AuthService;
import com.dging.dgingmarket.service.ProductService;
import com.dging.dgingmarket.service.cloud.FileUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@Import(MockS3Configuration.class)
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(RestDocumentationExtension.class)
public class BaseSpringBootTest {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected Environment env;

//    @Autowired
//    protected RestDocumentationResultHandler restDocs;

    @SpyBean
    protected ProductService productService;

    @SpyBean
    protected FileUploadService fileUploadService;

    @SpyBean
    protected AuthService authService;

    @MockBean
    protected ProductRepository productRepository;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected ImageRepository imageRepository;

    @MockBean
    protected TagRepository tagRepository;

    @Autowired
    protected MockMvc mockMvc;

    /*@BeforeEach
    void setUp(
            final WebApplicationContext context,
            final RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocs)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }*/
}
