<<<<<<< HEAD
//        package com.example.demo.controller;
//
//        import org.springframework.web.bind.annotation.RequestMapping;
//        import org.springframework.web.bind.annotation.RestController;
//
//        @RestController
//        public class HelloController
//        {
//            @RequestMapping("/hello")
//            public String helloWorld()
//            {
//                return "Hello World";
//            }
//        }
=======
package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String helloWorld()
    {
        return "Hello World";
    }
}
>>>>>>> dca96b3aced208d8c12d75805234b2ebe0e415ad
