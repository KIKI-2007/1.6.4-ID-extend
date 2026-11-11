package com.zxl.betterid;

import com.zxl.betterid.transformer.BetterIDTransformer;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.util.Map;

@IFMLLoadingPlugin.Name("BetterIDExtender")
@IFMLLoadingPlugin.MCVersion("1.6.4")
@IFMLLoadingPlugin.TransformerExclusions({"com.zxl.betterid"})
public class BetterIDLoadingPlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] { BetterIDTransformer.class.getName() };
    }
    @Override public String getModContainerClass() { return null; }
    @Override public String getSetupClass() { return null; }
    @Override public void injectData(Map<String, Object> data) { }
    @Override public String getAccessTransformerClass() { return null; }
}
