{
  'targets': [
    # your M2M/IoT application
    {
      'target_name': '<(package_name)',
      'sources': [ 'src/<(package_name).c' ],
      'product_prefix': '',
      'type': 'shared_library',
      'cflags': [ '-fPIC' ],
      'include_dirs' : [
      ],
      'libraries': [
        '-lev', '-luuid'
      ],
      'dependencies': [ 'moatapp' ],
    },

    # moat stub
    {
      'target_name': 'moatapp',
      'product_prefix': 'lib',
      'type': 'shared_library',
      'cflags': [ '-fPIC' ],
      'sources': [
        'stub/stub__moatapp.c',
      ],
    },

    # link test
    {
      'target_name': 'test',
      'type': 'executable',
      'dependencies': [ 'moatapp', '<(package_name)' ],
      'sources': [ 'test/test.c' ]
    },
  ],
}
