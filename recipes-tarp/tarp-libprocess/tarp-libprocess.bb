inherit tarp_common

SUMMARY = "C++ library for working with processes"
DESCRIPTION = "" 
SECTION = "${TARP_PACKAGE_SECTION}"
LICENSE = "CLOSED" 

# name of package;
PN := "tarp-libprocess"

# Without this, the build system renames tarp-libprocess to simply
# libprocess (-dev,-dbg etc) by taking the name from the .so file,
# which is indeed libprocess.so.
# We do not want that behavior here.
# See https://docs.yoctoproject.org/ref-manual/variables.html#term-DEBIAN_NOAUTONAME.
DEBIAN_NOAUTONAME:${PN} = "1"

# PR  = the revision of the recipe. Default value for this revision is "r0".
# Subsequent revisions of the recipe conventionally will have "r1", "r2" and
# so on.
PR = "r0" 

# PV = the version of the recipe. Normally extracted from the recipe file name.

# see https://docs.yoctoproject.org/ref-manual/variables.html#term-PACKAGES
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg ${PN}-staticdev"

PROVIDES = "${PN} ${PN}-dev"
RPROVIDES:${PN}-dev = "${PN}-dev"
DEPENDS += "asio libtarp"
RDEPENDS:${PN}-dev += "libstdc++"
SRC_URI = "git://github.com/dcrisn/libprocess.git;protocol=https;branch=main"
SRCREV ="872053351aa71d28a7ee7a581a83fa9b189500e9"

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
                    ${libdir}/libprocess.so \
"

FILES:${PN}-dbg += "\
                    ${libdir}/debug/* \
"

FILES:${PN} += "${libdir}/libprocess.so.*"


