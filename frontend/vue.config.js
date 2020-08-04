module.exports = {
    runtimeCompiler: true,
    devServer: {
        proxy: {
            '/api': {
                // target: "http://i3a205.p.ssafy.io:8999",
                target: "http://localhost:8999",
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '/travel/api'
                }
            }
        },
        disableHostCheck: true,
        port: 8090
    }
}