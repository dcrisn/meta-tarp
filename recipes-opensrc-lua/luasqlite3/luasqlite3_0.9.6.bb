DESCRIPTION = "Official Lua bindings for sqlite3"

# 'creative commons': sqlite (and its lua bindings) is in the Public Domain
LICENSE = "CC0-1.0"
HOMEPAGE = "https://lua.sqlite.org/"
LIC_FILES_CHKSUM = "file://lsqlite3.c;beginline=1;endline=27;md5=4864c158e886b39b5e81454f7950e5da"

DEPENDS += "lua sqlite3"
RDEPENDS:${PN} += "lua sqlite3"
PV := "0.9.6"
SRC_URI = "http://lua.sqlite.org/home/zip/lsqlite3_v096.zip?uuid=v${PV};downloadfilename=luasqlite3-v${PV}.zip \
"
SRC_URI[md5sum] = "70e87bc7efa1ec4a7d69ccebdcd91e09"

# sources get extracted to this directory, so we must set it appropriately.
S = "${WORKDIR}/lsqlite3_v096"
LUA_VERSION = "5.4"
B = "${S}"

COMPILE_FLAGS = '\
    -DLUAINC="\"${STAGING_DIR_TARGET}${includedir}/lua${LUA_VERSION}\"" \
    -DLSQLITE_VERSION="\"${PV}\"" \
    ${CFLAGS} \
    ${CPPFLAGS} \
    -I${STAGING_INCDIR}/sqlite3 \
    -I${STAGING_INCDIR}/lua \
    ${LDFLAGS} \
'

do_configure[noexec] = '1'

do_compile() {
    ${CC} ${S}/lsqlite3.c ${COMPILE_FLAGS} -shared -o ${S}/lsqlite3.so
}

do_install() {
    install -d ${D}${libdir}/lua/${LUA_VERSION}
    install -m 0644 ${S}/lsqlite3.so ${D}${libdir}/lua/${LUA_VERSION}/
}

FILES:${PN} = "${libdir}/lua/${LUA_VERSION}"
