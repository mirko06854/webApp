const path = require('path');

module.exports = {
    entry: '/src/main/java/front/index.js', // Replace with your entry file
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist'), // Replace with your output directory
    },
    devServer: {
        static: './dist', // Replace with your output directory
        port: 8081,
    },
};
