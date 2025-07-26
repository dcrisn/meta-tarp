DESCRIPTION = "Lua bindings for POSIX APIs."
LICENSE = "MIT"
HOMEPAGE = "https://github.com/luaposix/luaposix"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f30d022f6ef53952fa87cc0b6fffb153"

DEPENDS += "lua-native lua virtual/crypt"
#DEPENDS += "lua virtual/crypt"

SRC_URI = "git://github.com/luaposix/luaposix.git;branch=release-v36.2;protocol=https \
"
SRCREV = "5a8d8c768fc3c51f42cb591e9523a60399efc6a1"
S = "${WORKDIR}/git"
LUA_VERSION = "5.4"

B = "${S}"

PREFERRED_PROVIDER_luaposix = "luaposix"

inherit pkgconfig

do_compile() {
    # luaposix is built by invoking a lua script which servers as a build tool.
    # This means we have to invoke the host lua on it.
    # The script itself ('luke') is documented here:
    # https://github.com/gvvaughan/luke.
    # It performs text substitution on a 'lukefile' based on variables either
    # passed as arguments or otherwise taken from the environment:
    # > The VALUE for all VARIABLE=VALUE pairs on the luke command line are
    # > substituted in the lukefile wherever $VARIABLE is seen, otherwise
    # > $VARIABLE will be looked up in the process environment.
    # So for this to compile, we need to pass args as appropriate.
    ${LUA} ${S}/build-aux/luke \
        package=luaposix \
        version="${LUA_VERSION}" \
        CC="${CC}" \
        LD="${CC}" \
        CFLAGS="${CFLAGS}" \
        LDFLAGS="${LDFLAGS}" \
        PREFIX="${prefix}" \
        LUA_INCDIR="${STAGING_DIR_TARGET}${includedir}/lua${LUA_VERSION}" \
        INST_LUADIR="${datadir}/lua/${LUA_VERSION}"
}

do_install() {
    ${S}/build-aux/luke PREFIX=${D}${prefix} INST_LIBDIR=${D}${libdir}/lua/${LUA_VERSION} install
}

FILES:${PN} = "${datadir}/lua/${LUA_VERSION} \
               ${libdir}/lua/${LUA_VERSION}"
