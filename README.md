<h1 align="center">Bloggy</h1>
<p align="center">  
Bloggy is an android app for personal blogging (News , Personal blog , Content sharing ..... ) based on modern Android application -MVVM architecture you can  fetching data from the network and integrating persisted data in the database via repository pattern.

</p>
</br>


<p align="center">
<img src="/previews/screenshot.png"/>
</p>
<img src="/previews/preview.gif" align="right" width="32%"/>

## Download
Go to the [Releases](https://github.com/sudoGunner/Bloggy/releases) to download the latest APK.

## Core Features :
- user friendly and that's come with nice looking ui .
- work offline.
- add content to read later.
- tracking user reading list .
- offline search .

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt (alpha) for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  
  
  ## Architecture
Bloggy is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)


## Find this repository useful? :heart:
[follow](https://github.com/sudoGunner) me for my next creations! 🤩

# License
```xml
Designed and developed by 2020 sudoGunner(Youssef El Mountahi)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
