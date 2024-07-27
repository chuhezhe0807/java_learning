package com.chuhezhe.webapplication.controller;

import com.google.code.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ClassName: CaptchaController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/24 23:17
 * @Version 1.0
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    private final Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    private final Producer producer;

    public CaptchaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/pic.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String capText = producer.createText();
        logger.info("验证码: {}", capText);

        request.getSession().setAttribute("captcha", capText);
        BufferedImage image = producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(image, "jpg", out);
        out.flush();
    }
}
