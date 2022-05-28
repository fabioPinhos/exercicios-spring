package br.com.library.api.library.api.resources;

import br.com.library.api.library.entity.Book;
import br.com.library.api.library.dto.BookDTO;
import br.com.library.api.library.exception.BusinessException;
import br.com.library.api.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService service;

    @Test
    @DisplayName("Deve criar um livro")
    public void createBookTest() throws Exception {

        BookDTO dto = createNewBook();

        Book entity = createNewEntity();

        BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(entity);

        //ModelMapper mapeia um objeto para JSON
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("nome").value("Meu livro"))
                .andExpect(jsonPath("autor").value("Autor"))
                .andExpect(jsonPath("isbn").value("isbn"));

    }

    @Test
    @DisplayName("Não deve criar um Book, devido ao objeto ser invalido")
    public void createInvalidBookTest() throws Exception{

        //ModelMapper mapeia um objeto para JSON
        String json = new ObjectMapper().writeValueAsString(new BookDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(4)));

    }

    @Test
    @DisplayName("Deve lancar erro ao tentar cadastrar um livro com isbn ja utilizado por outro")
    public void createBookWithDuplicatedIsbn() throws Exception{

        BookDTO dto = createNewBook();
        //ModelMapper mapeia um objeto para JSON
        String json = new ObjectMapper().writeValueAsString(dto);
        String mensagemErro = "Isbn já cadastrado.";

        BDDMockito.given(service.save(Mockito.any(Book.class)))
                .willThrow(new BusinessException(mensagemErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect( jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(mensagemErro));


    }

    private BookDTO createNewBook() {
        return BookDTO.builder().autor("Autor").nome("Meu livro").isbn("isbn").titulo("Qualquer").build();
    }

    private Book createNewEntity() {
        return Book.builder().id(10).autor("Autor").nome("Meu livro").isbn("isbn").build();
    }

}
