DESCRIPTION = "Lua library for securing socket connections"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/lunarmodules/luasec"
LIC_FILES_CHKSUM = "file://LICENSE;md5=83549a4e70ba33fe3d4277f266634131"

DEPENDS += "lua luasocket openssl"
RDEPENDS:${PN} += "lua luasocket openssl"

SRC_URI = "git://github.com/lunarmodules/luasec.git;branch=master;protocol=https \
"
SRCREV = "4c06287052d68fdbe7429b8f967cdc8ee94aa44a"
S = "${WORKDIR}/git"
LUA_VERSION = "5.4"

B = "${S}"

inherit module

EXTRA_OEMAKE += "INC_PATH='-I${STAGING_INCDIR}'"
EXTRA_OEMAKE += "LIB_PATH=''"
EXTRA_OEMAKE += "CC='${CC}'"
EXTRA_OEMAKE += "LD='${CC}'"
EXTRA_OEMAKE += "LUAPATH='${libdir}/lua/'"
EXTRA_OEMAKE += "LUACPATH='${libdir}/lua/'"
EXTRA_OEMAKE += "DESTDIR='${D}'"

do_compile() {
    oe_runmake ${EXTRA_OEMAKE} linux
}

do_install() {
    oe_runmake install
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION} \
               ${libdir}/lua/${LUA_VERSION} \
               ${libdir}/lua/ \
"
