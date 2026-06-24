package karate;

import com.intuit.karate.junit5.Karate;

class ApiTest {

    @Karate.Test
    Karate testPosts() {
        return Karate.run("classpath:features/api/posts.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testUsers() {
        return Karate.run("classpath:features/api/users.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testAuth() {
        return Karate.run("classpath:features/api/auth.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testSecurity() {
        return Karate.run("classpath:features/security/security.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testCsirtReporting() {
        return Karate.run("classpath:features/security/csirt-reporting-contract.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:features/api").relativeTo(getClass());
    }
}
