# 文本小部件（TextWidget）

一个 Android 原生应用，提供可自由调整大小的桌面文本小部件。

## 功能

- 在桌面添加文本小部件
- 小部件支持水平 + 垂直方向任意调整大小
- 点击小部件随时编辑文本
- 每个小部件独立保存文本内容
- 最小 120dp × 120dp，无上限

## 使用方法

1. 安装 APK 后，长按桌面空白处
2. 选择「小部件」或「添加小部件」
3. 找到「文本小部件」，拖动到桌面
4. 在弹出的配置页面输入文本，点确认
5. 长按小部件可调整大小，点击小部件可修改文本

## 在线编译

### 方式一：GitHub Actions（推荐）

1. 注册 GitHub 账号，新建一个仓库
2. 把本项目所有文件上传到仓库（保持目录结构）
3. 进入仓库的 **Actions** 标签页，手动触发 **Build APK** workflow
4. 等待构建完成（约 2-3 分钟）
5. 在 workflow 运行详情页的 **Artifacts** 区域下载 `app-debug.apk`

### 方式二：Gitee 安卓构建（国内访问快）

1. 注册 Gitee 账号，新建仓库并上传代码
2. 进入仓库 → 管理 → 基本信息 → 语言选择「Android」
3. 进入「服务」→「APK 在线构建」
4. 配置构建参数后启动构建，完成后下载 APK

### 方式三：本地编译（有电脑时）

安装 Android Studio，打开本项目，等待 Gradle 同步完成后：
- 菜单 Build → Build Bundle(s) / APK(s) → Build APK(s)
- APK 输出在 `app/build/outputs/apk/debug/app-debug.apk`

## 技术栈

- Kotlin
- minSdk 24（Android 7.0+）
- targetSdk 34
- App Widget（原生桌面小部件）
- SharedPreferences 存储
