MOAT IoT Application Example
========
Hicharts JS Example Javascript Application
--------

This application is running on IIDN server sandbox.

See [the tutorial](http://dev.yourinventit.com/guides/get-started) to learn more.

The directory structure of this application is as follows:

    |-- pvdemo

- Containing MOAT js javascript files and the application metadata named `` package.json ``

You can perform unit testing by the following command:

    rake

This will run tests which are identified by their file names '*.test.js'. See [here](https://github.com/inventit/moatjs-stub) for detail.

You can also create a zip package to deploy by:

    rake pacakge

The created file is found under the `pkg` directory.

The package file can be cleaned by:

    rake clobber_package
