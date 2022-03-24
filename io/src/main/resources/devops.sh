#!/bin/bash
# 第一个参数：项目代码目录，第二个参数：项目名称
projectPath=$1
projectName=$2

# 进入到前端项目的根目录
cd $projectPath
echo $projectPath
# 打包前端
node -v
#/usr/local/bin/npm run build
# 部署到Nginx
# cp -r ./build/ /Users/saya/project/java/java-utils/io/$projectName