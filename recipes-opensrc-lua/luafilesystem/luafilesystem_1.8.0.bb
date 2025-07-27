DESCRIPTION = "Lua Filesystem API extensions"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d9b7e441d51a96b17511ee3be5a75857"

DEPENDS += "lua"

SRC_URI = "git://github.com/lunarmodules/luafilesystem.git;branch=master;protocol=https \
           file://0001-add-LDFLAGS-to-placate-yocto-qa-check.patch \
           "
SRCREV = "7c6e1b013caec0602ca4796df3b1d7253a2dd258"
S = "${WORKDIR}/git"
LUA_VERSION = "5.4"

inherit module

EXTRA_OEMAKE = "\
    LUA_VERSION='${LUA_VERSION}' \
    CC='${CC}' \
    LD='${CC}' \
    CFLAGS='${CFLAGS}' \
    LDFLAGS='${LDFLAGS}' \
"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE}
}

do_install() {
    oe_runmake PREFIX='${D}/usr' install
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION} \
               ${libdir}/lua/${LUA_VERSION}/*"
