inherit tarp_common

SUMMARY = "Semantic versioning Lua library"
DESCRIPTION = "" 
SECTION = "${TARP_PACKAGE_SECTION}"
LICENSE = "CLOSED" 

# PN = the name of the recipe -- usually extracted from the recipe file name.

# PR  = the revision of the recipe. Default value for this revision is "r0".
# Subsequent revisions of the recipe conventionally will have "r1", "r2" and
# so on.
PR = "r0" 

# PV = the version of the recipe. Normally extracted from the recipe file name.

# see https://docs.yoctoproject.org/ref-manual/variables.html#term-PACKAGES
PACKAGES = "${PN}"

PROVIDES = "${PN}"

# we need >=lua5.3 but yocto, wait for it, has no wait to express this
# and have it be enforced reliably..
RDEPENDS:${PN} = "lua"
LUA_VERSION_COMPAT := "5.3 5.4"
SRC_URI = "git://github.com/dcrisn/mover.git;protocol=https;branch=main"
SRCREV ="aa3c78a11ca587ae3fa39d7b33c7f9aa032d0f22"

S = "${WORKDIR}/git"

# add this to the file search path so it can be found in install rules
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# No configure or compile steps
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    ${INSTALL_DIR} ${D}/${TARP_SYSTEM_LUA_LIB_DIR}
    ${INSTALL_DATA} ${S}/src/* ${D}/${TARP_SYSTEM_LUA_LIB_DIR}/
}

do_install[prefuncs] += "check_lua_version"

FILES:${PN} += "${TARP_SYSTEM_LUA_LIB_DIR}/*"

