{
  'variables': {
    'target_arch%': 'ia32',
    'host_arch%': 'ia32',
    'library%': 'shared_library',
    'gcc_version%': 'unknown',
    'clang%': 0,
    'python%': 'python',
  },
  'target_defaults': {
    'default_configuration': 'Release',
    'configurations': {
      'Debug': {
        'defines': [ 'DEBUG', '_DEBUG' ],
        'cflags': [ '-g', '-O0' ],
      },
      'Release': {
        'cflags' : [ '-O2', '-fno-strict-aliasing' ],
      }
    },
    'include_dirs': [
      'include',
      './src'
    ],
    'conditions': [
      [ 'OS=="linux"', {
        'cflags': [ '-Wall', ],
        'ldflags': [ '-rdynamic' ],
        'conditions': [
          [ 'target_arch=="ia32"', {
            'cflags': [ '-m32' ],
            'ldflags': [ '-m32' ],
          }],
          [ 'target_arch=="x64"', {
            'cflags': [ '-m64' ],
            'ldflags': [ '-m64' ],
          }],
        ],
      }],
    ],
  },
}
