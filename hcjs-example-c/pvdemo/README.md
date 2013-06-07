MOAT C SDK
===

This is a Software Development Kit enabling you to develop MOAT C client application.

## What is MOAT C?
[MOAT C](http://dev.yourinventit.com/references/moat-c-api-document) is a C API to build a client side application interacting with cloud server.
It is a part of [MOAT IoT](http://dev.yourinventit.com/guides/moat-iot), which is a specification set of creating IoT/M2M applications running on Inventit® ServiceSync environment.

*****
The library offers you:

* to build and package a MOAT C client application.

## Prerequisites
* gyp
* python 2.6 or 2.7
* GNU Make 3.81 or later
* gcc 4.2 or later

## How to use

### 1. Download a security token for your application package using [IIDN-CLI Tool](https://github.com/inventit/iidn-cli).
    $ sh $CLI/iidn tokengen simple-example

### 2. Deploy your security token into SDK
Copy the downloaded security token into '${SDK_ROOT}/package' as 'token.bin'
    $ cp 1363966226152-token.bin ${SDK_ROOT}/package/token.bin

### 3. Configure and write code
Perform the 'configure' command
    $ cd ${SDK_ROOT}
    $ ./configure
Then, a template source code is deployed in ${SDK_ROOT}/src/${PACKAGE_NAME}.c
Write your application code.

#### configure options
    $ ./configure --help
    Options:
      -h, --help           show this help message and exit
      --debug              Also build debug build
      --dest-cpu=DEST_CPU  CPU architecture to build for. Valid values are: arm,
                           ia32, x64
      --name=PACKAGE_NAME  Package name of your M2M/IoT application
      --token=TOKEN_PATH   Path to the security token file. Default path is
                          './package/token.bin'

#### Cross compile (example)
    $ export CROSS=arm-linux-gnueabi-
    $ export CC=${CROSS}gcc
    $ export CXX=${CROSS}g++
    $ export AR=${CROSS}ar
    $ export RANLIB=${CROSS}ranlib
    $ export strip=${CROSS}strip
    $ cd ${SDK_ROOT}
    $ ./configure --dest-cpu=arm

### 4. Build your application and create a package
    $ make
    $ make package

### 5. Deploy onto Inventit IoT Developer Network(IIDN) Sandbox Server.
You can deploy the created package onto Inventit IoT Developer Network(IIDN) Sandbox Server.
Visit [IIDN site](http://dev.yourinventit.com) 
    $ sh $CLI/iidn bindeploy simple-example.zip

## Where can you deploy?

You can deploy the created package onto Inventit IoT Developer Network(IIDN) Sandbox Server.
Visit [IIDN site](http://dev.yourinventit.com) 

## Source Code License

All program source codes are available under the MIT style License.

The use of IIDN service requires [our term of service](http://dev.yourinventit.com/legal/term-of-service).

Copyright (c) 2013 Inventit Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Change History

1.0.0 : June 6, 2013

* Initial Release.

