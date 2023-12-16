//const { defineConfig } = require('@vue/cli-service')
module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080', //  axios로 백엔드와 통신하기위한 url
                changeOrigin: true,
                pathRewrite: {
                    '^/': ''
                }
            }
        }
    }/*,
    publicPath: '/home'*/
}