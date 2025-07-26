DESCRIPTION = "Lua network library"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/lunarmodules/luasocket"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d5850c0e7e7928460cd638a38f062263"

DEPENDS += "lua"
RDEPENDS:${PN} += "lua"

SRC_URI = "git://github.com/lunarmodules/luasocket.git;branch=master;protocol=https \
"
SRCREV = "95b7efa9da506ef968c1347edf3fc56370f0deed"
S = "${WORKDIR}/git"
LUA_VERSION = "5.4"

B = "${S}"

inherit module

EXTRA_OEMAKE = "\
    LUAINC='${STAGING_DIR_TARGET}${includedir}/lua${LUA_VERSION}' \
    LUAV='${LUA_VERSION}' \
    PLAT='linux' \
    CC='${CC}' \
    LD='${CC}' \
    CFLAGS='${CFLAGS}' \
    MYLDFLAGS='-llua ${LDFLAGS}' \
"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE}
}

do_install() {
    bbwarn "running"
    oe_runmake DESTDIR=${D}/ install
    oe_runmake DESTDIR=${D}/ install-unix
}


FILES:${PN} = "${datadir}/lua/${LUA_VERSION} \
               ${libdir}/lua/${LUA_VERSION}"
