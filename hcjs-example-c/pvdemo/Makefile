-include config.mk

BUILDTYPE ?= Release
PYTHON ?= python
DESTDIR ?=
SIGN ?=
PREFIX ?= /usr/local

ifeq ($(BUILDTYPE),Release)
all: out/Makefile moatapp
else
all: out/Makefile moatapp_g
endif

.PHONY: moatapp moatapp_g test package clean distclean

moatapp: config.gypi out/Makefile
	$(MAKE) -C out BUILDTYPE=Release V=$(V)

moatapp_g: config.gypi out/Makefile
	$(MAKE) -C out BUILDTYPE=Debug V=$(V)

out/Makefile: common.gypi moatapp.gyp config.gypi
	$(PYTHON) tools/gyp_moatapp -f make

config.gypi: configure
	$(PYTHON) ./configure

package: all
	$(PYTHON) tools/package.py

clean:
	-rm -rf out/Makefile moatapp moatapp_g out/$(BUILDTYPE)/*
	-find out/ -name '*.o' -o -name '*.a' | xargs rm -rf

distclean:
	-rm -rf out
	-rm -f config.gypi
	-rm -f config.mk

test: all
	@echo "$@: not supported yet."

