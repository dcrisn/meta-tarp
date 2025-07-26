inherit tarp_common

SUMMARY = "C++ Lifetime utilities library"
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
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg ${PN}-staticdev"

PROVIDES = "${PN} ${PN}-dev"
RPROVIDES:${PN}-dev = "${PN}-dev"
DEPENDS += ""
RDEPENDS:${PN}-dev += "libstdc++"
SRC_URI = "git://github.com/dcrisn/lifetime_utils.git;protocol=https;branch=main"
SRCREV ="d463974bf7bfe795f73580c07a3beb1e6d5d46a5"

S = "${WORKDIR}/git"

# get cmake and pkconfig functionality through bibtbake classes
inherit cmake pkgconfig

# add this to the file search path so it can be found in install rules
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

EXTRA_OECMAKE += " -DCMAKE_BUILD_TYPE=Release \
 "

# The development package installs the headers and the .cmake files that
# other package may look for.

FILES:${PN}-dev += "/include/* \
                   "
