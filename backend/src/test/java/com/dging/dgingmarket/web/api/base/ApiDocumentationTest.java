package com.dging.dgingmarket.web.api.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

@AutoConfigureRestDocs
public abstract class ApiDocumentationTest extends BaseSpringBootTest {

    @BeforeAll
    public void setup() {
    }

    @AfterAll
    public void teardown() {
    }

    //COMMON_DESC
    protected ParameterDescriptor[] pageParams = new ParameterDescriptor[] {
            parameterWithName("page").description("조회할 페이지입니다. 0부터 시작합니다.").optional(),
            parameterWithName("size").description("한 페이지에 보여줄 사이즈 수입니다.").optional(),
            parameterWithName("sort").description("정렬 기준입니다.").optional()
    };

    protected FieldDescriptor[] pageFields = new FieldDescriptor[] {
            fieldWithPath("content").type(JsonFieldType.ARRAY).description("페이지 요소 리스트입니다.").optional(),
            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부입니다."),
            fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 요소 수입니다."),
            fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수입니다."),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("한 페이지에 보여줄 사이즈 수입니다."),
            fieldWithPath("number").type(JsonFieldType.NUMBER).ignored(),
            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부입니다."),
            fieldWithPath("numberOfElements").ignored(),
            fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("리스트가 비어 있는지 여부입니다."),
            subsectionWithPath("sort").type(JsonFieldType.OBJECT).ignored(),
            subsectionWithPath("pageable").type(JsonFieldType.OBJECT).ignored(),
    };

    protected FieldDescriptor[] badFields = new FieldDescriptor[] {
            fieldWithPath("code").description("에러 코드입니다."),
            fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 코드입니다."),
            fieldWithPath("message").description("에러 메시지입니다."),
            fieldWithPath("errors").description("필드 에러입니다. 필드 검증 시에만 존재합니다.")
    };

    protected FieldDescriptor[] errorsFields = new FieldDescriptor[] {
            fieldWithPath("field").type(JsonFieldType.STRING).description("검증에 실패한 필드명입니다."),
            fieldWithPath("value").type(JsonFieldType.STRING).description("검증에 실패한 필드값입니다."),
            fieldWithPath("reason").type(JsonFieldType.STRING).description("검증에 실패한 이유입니다.")
    };

    //VALUE_DESC


    //USER_DESC
    protected FieldDescriptor[] socialRequestFields = new FieldDescriptor[] {
            fieldWithPath("accessToken").type(JsonFieldType.STRING).type(JsonFieldType.STRING).description("SNS로부터 받은 액세스 토큰입니다."),
    };

    protected HeaderDescriptor[] jwtHeader = new HeaderDescriptor[] {
            headerWithName("Authorization").description("로그인 성공 시 발급받은 액세스토큰입니다.")
    };

    protected FieldDescriptor[] productsResponseFields = new FieldDescriptor[] {
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("상품 아이디입니다."),
            fieldWithPath("storeId").type(JsonFieldType.NUMBER).description("상점 아이디입니다."),
            fieldWithPath("storeName").type(JsonFieldType.STRING).description("상점명입니다."),
            fieldWithPath("title").type(JsonFieldType.STRING).description("제목입니다."),
            fieldWithPath("runningStatus").type(JsonFieldType.STRING).description("진행 상황입니다."),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격입니다."),
            fieldWithPath("imageUrls").type(JsonFieldType.ARRAY).description("이미지 URL 배열입니다."),
            fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그 배열입니다.").optional(),
            fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성일시입니다."),
    };

    protected FieldDescriptor[] imageResponseFields = new FieldDescriptor[] {
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("이미지 아이디입니다."),
            fieldWithPath("url").type(JsonFieldType.STRING).description("이미지 URL 입니다.")
    };

    protected FieldDescriptor[] tagResponseFields = new FieldDescriptor[] {
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("태그 아이디입니다."),
            fieldWithPath("name").type(JsonFieldType.STRING).description("태그명입니다.")
    };

}
