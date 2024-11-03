package com.dging.dgingmarket.web.api.base;

import com.dging.dgingmarket.domain.common.ImageRepository;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.env.Environment;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


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

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected ImageRepository imageRepository;

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
