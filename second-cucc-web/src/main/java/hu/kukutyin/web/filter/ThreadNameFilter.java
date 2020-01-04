package hu.kukutyin.web.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import hu.kukutyin.core.utils.ThreadNameFromSessionId;
import hu.kukutyin.core.utils.StringUtil;

public class ThreadNameFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ThreadNameFilter.class);
    private static final Random RANDOM = new Random();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long t = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String sessionId = StringUtils.right(session.getId(), 5);
        String service = StringUtil.substringBeforeLastStringFromBeforeLastSeparator(req.getRequestURI(), "/");
        sessionId = sessionId + "-" + req.getMethod() + "-" + service + "-" + RANDOM.nextInt(100);

        Thread currentThread = Thread.currentThread();
        String oldName = currentThread.getName();
        ThreadNameFromSessionId.set(sessionId);
        logger.debug(String.format("Thread name set. Duration: %s ms", System.currentTimeMillis() - t ));
        try {
            t = System.currentTimeMillis();
            chain.doFilter(request, response);
            logger.debug(String.format("Request ended. Duration: %s ms", System.currentTimeMillis() - t ));
        } finally {
            currentThread.setName(oldName);
        }
    }

    @Override
    public void destroy() {
        logger.info("ThreadNameFilter - destroy");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("ThreadNameFilter - init");
    }
}
