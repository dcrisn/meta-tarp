the configs directory contains customized Linux kernel `.config`
files, generated via the Linux kernel `menuconfig` Make target.
They are not actually 'defconfig' but it appears this name is treated
automagically (yocto picks up your file if named as such and applies it).
