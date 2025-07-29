# for packages that depend on lua at build-time
# (i.e. via DEPENDS=), we need the lua interepreter
# to be put in the sysroot as well, so we can check
# the version via the `strings` command.
SYSROOT_DIRS:append = " ${bindir}"
