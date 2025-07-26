inherit tarp_common

SUMMARY = "C++ state machine library"
DESCRIPTION = "" 
SECTION = "${TARP_PACKAGE_SECTION}"

LICENSE = "BSL-1.0" 
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=e4224ccaecb14d942c71d31bef20d78c"

# PN = the name of the recipe -- usually extracted from the recipe file name.

# PR  = the revision of the recipe. Default value for this revision is "r0".
# Subsequent revisions of the recipe conventionally will have "r1", "r2" and
# so on.
PR = "r0" 

# PV = the version of the recipe. Normally extracted from the recipe file name.

# see https://docs.yoctoproject.org/ref-manual/variables.html#term-PACKAGES
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

PROVIDES = "${PN} ${PN}-dev"
RPROVIDES:${PN}-dev = "${PN}-dev sml"
SRC_URI = "git://github.com/boost-ext/sml.git;protocol=https;branch=master"
SRCREV ="f232328b49adf708737bef5173d5620855d88446"

S = "${WORKDIR}/git"

# get cmake and pkconfig functionality through bibtbake classes
inherit cmake pkgconfig

# add this to the file search path so it can be found in install rules
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# The development package installs the headers and the .cmake files that
# other package may look for.

FILES:${PN}-dev += "/include/* \
                    ${libdir}/cmake/* \
                   "
