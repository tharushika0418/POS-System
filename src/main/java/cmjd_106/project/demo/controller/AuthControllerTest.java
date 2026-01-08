// package cmjd_106.project.demo.controller;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// /**
//  * Test class for Authentication endpoints
//  * Tests user registration and login functionality
//  */
// @SpringBootTest
// @AutoConfigureMockMvc
// public class AuthControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Test
//     public void testUserRegistration() throws Exception {
//         String signupJson = "{" +
//                 "\"username\":\"testuser\"," +
//                 "\"email\":\"test@example.com\"," +
//                 "\"password\":\"password123\"" +
//                 "}";
        
//         mockMvc.perform(post("/auth/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(signupJson))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("User registered successfully!"));
//     }

//     @Test
//     public void testUserLogin() throws Exception {
//         // First register a user
//         String signupJson = "{" +
//                 "\"username\":\"logintest\"," +
//                 "\"email\":\"logintest@example.com\"," +
//                 "\"password\":\"password123\"" +
//                 "}";
        
//         mockMvc.perform(post("/auth/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(signupJson));

//         // Then try to login
//         String loginJson = "{" +
//                 "\"username\":\"logintest\"," +
//                 "\"password\":\"password123\"" +
//                 "}";
        
//         mockMvc.perform(post("/auth/login")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(loginJson))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").exists())
//                 .andExpect(jsonPath("$.username").value("logintest"));
//     }

//     @Test
//     public void testDuplicateUsername() throws Exception {
//         String signupJson = "{" +
//                 "\"username\":\"duplicate\"," +
//                 "\"email\":\"duplicate1@example.com\"," +
//                 "\"password\":\"password123\"" +
//                 "}";
        
//         // First registration should succeed
//         mockMvc.perform(post("/auth/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(signupJson))
//                 .andExpect(status().isOk());

//         // Second registration with same username should fail
//         String duplicateJson = "{" +
//                 "\"username\":\"duplicate\"," +
//                 "\"email\":\"duplicate2@example.com\"," +
//                 "\"password\":\"password123\"" +
//                 "}";
        
//         mockMvc.perform(post("/auth/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(duplicateJson))
//                 .andExpect(status().isBadRequest());
//     }
// }