inherit tarp_common

SUMMARY = "Core tarp C&C++ library"
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
SRC_URI = "git://github.com/dcrisn/libtarp.git;protocol=https;branch=tmp"
SRCREV ="e8ae2e10047f135d732b59aa171c99487e99af98"

S = "${WORKDIR}/git"

# get cmake and pkconfig functionality through bibtbake classes
inherit cmake pkgconfig

# add this to the file search path so it can be found in install rules
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

EXTRA_OECMAKE += " -DCMAKE_BUILD_TYPE=Release \
 "

# The development package installs the headers and the .cmake files that
# other package may look for.

FILES:${PN}     += "${libdir}/libtarp.so.* \
                    ${bindir}/* \
                    "
# yocto wants unversioned (i.e. pure .so) in the dev package
FILES:${PN}-dev += "/lib/cmake/* \
                    /lib/pkgconfig/* \
                    ${libdir}/libtarp.so \
                    "
FILES:${PN}-dbg += "${libdir}/debug/* \
                    ${bindir}/debug/* \
                    "
