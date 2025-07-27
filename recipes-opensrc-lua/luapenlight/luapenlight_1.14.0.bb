DESCRIPTION = "Plain Lua python-inspired library utils"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/lunarmodules/Penlight"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=0a2993b604147dcce1ea113b72b618cd"

DEPENDS += ""
RDEPENDS:${PN} += "lua"

SRC_URI = "git://github.com/lunarmodules/Penlight.git;branch=master;protocol=https \
"
SRCREV = "e2e5f6477bd060f0d94349662f24ac1ecfdf543e"
S = "${WORKDIR}/git"
LUA_VERSION = "5.4"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/lua/${LUA_VERSION}/penlight
    cp -r ${S}/lua/pl/*.lua ${D}${datadir}/lua/${LUA_VERSION}/penlight/
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION}/penlight"
