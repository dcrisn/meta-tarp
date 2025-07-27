DESCRIPTION = "Basic json encoder/decoder in pure Lua"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/craigmj/json4lua"
LIC_FILES_CHKSUM = "file://doc/LICENCE.txt;md5=0989eedf014c09bc1415a54cbc1086d5"

DEPENDS += ""
RDEPENDS:${PN} += "lua luasocket"

SRC_URI = "git://github.com/craigmj/json4lua.git;branch=master;protocol=https \
"
SRCREV = "a0da807dca77baf07d287631f5ad41a9097fc25c"
S = "${WORKDIR}/git"
LUA_VERSION = "5.4"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/lua/${LUA_VERSION}/json
    cp -r ${S}/json/json.lua ${D}${datadir}/lua/${LUA_VERSION}/
    cp -r ${S}/json/rpc*.lua ${D}${datadir}/lua/${LUA_VERSION}/json/
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION}/"
